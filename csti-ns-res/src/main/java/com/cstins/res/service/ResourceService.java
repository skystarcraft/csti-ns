package com.cstins.res.service;

import com.cstins.res.dao.DownloadrecordDao;
import com.cstins.res.dao.ResourceDao;
import com.cstins.res.entity.Downloadrecord;
import com.cstins.res.entity.Resource;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import net.sf.json.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.Date;
import java.util.List;

/**
 * @program: csti-ns
 * @description: 资源相关服务
 * @author: 杨云龙
 **/

@Service
@Transactional
public class ResourceService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private UserService userService;

    @Autowired
    private DownloadrecordDao downloadrecordDao;

    private static final String REDISKEY = "csti:res";

    public boolean delRes(Integer rid) {
        try {
            String pathFile = getRes(rid).getRaddr();
            storageClient.deleteFile(pathFile);
            resourceDao.deleteById(rid);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean uploadRes (Resource resource) {
        if (resource == null) return false;
        try {
            resourceDao.save(resource);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public com.alibaba.fastjson.JSONObject downloadRes(Integer rid, Integer uid) {
        com.alibaba.fastjson.JSONObject result = new com.alibaba.fastjson.JSONObject();
        resourceDao.addDownload(rid);
        Resource resource = null;
        Object res = redisTemplate.opsForHash().get(REDISKEY, "res:" + rid);
        if (res != null && !"".equals(res) && !"null".equals(res)) {
            JSONObject jsonObject = JSONObject.fromObject(res);
            resource = (Resource) JSONObject.toBean(jsonObject, Resource.class);
        } else {
            resource = resourceDao.findById(rid).get();
            JSONObject jsonObject = JSONObject.fromObject(resource);
            redisTemplate.opsForHash().put(REDISKEY, "res:" + rid, jsonObject.toString());
        }
        Integer score = resource.getRscore();
        boolean b = userService.reduceScore(score, uid);
        if (b) {
            Downloadrecord downloadrecord = new Downloadrecord();
            downloadrecord.setDowndate(new Date(new java.util.Date().getTime()));
            downloadrecord.setUid(uid);
            downloadrecord.setRid(rid);
            downloadrecord.setRname(resource.getRname());
            downloadrecordDao.save(downloadrecord);
        }
        if (b) {
            result.put("code", 200);
            result.put("msg", "获取成功！");
            result.put("data", resource.getRaddr());
        } else {
            result.put("code", 400);
            result.put("msg", "积分不足！");
            result.put("data", "");
        }
        return result;
    }

    public Resource getRes(Integer rid) {
        Resource resource = null;
        try {
            resource = resourceDao.findById(rid).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resource;
    }

    public List<Resource> getAllRes() {
        return resourceDao.findAll();
    }

    public List<Resource> getAllResByUid(Integer uid) {
        return resourceDao.findAllByRuidEquals(uid);
    }

    /**
     *	MultipartFile类型的文件上传ַ
     * @param file
     * @return
     * @throws IOException
     *
     */
    public String uploadFile(MultipartFile file) {
        StorePath storePath = null;
        try {
            storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
                    FilenameUtils.getExtension(file.getOriginalFilename()), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (storePath != null) {
            return getResAccessUrl(storePath);
        } else {
            return null;
        }
    }

    /**
     * 普通的文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    public String uploadFile(File file) {
        StorePath path = null;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            path = storageClient.uploadFile(inputStream, file.length(),
                    FilenameUtils.getExtension(file.getName()), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (path != null) {
            return getResAccessUrl(path);
        } else {
            return null;
        }
    }

    /**
     * 带输入流形式的文件上传
     *
     * @param is
     * @param size
     * @param fileName
     * @return
     */
    public String uploadFileStream(InputStream is, long size, String fileName) {
        StorePath path = storageClient.uploadFile(is, size, fileName, null);
        return getResAccessUrl(path);
    }

    /**
     * 将一段文本文件写到fastdfs的服务器上
     *
     * @param content
     * @param fileExtension
     * @return
     */
    public String uploadFile(String content, String fileExtension) {
        byte[] buff = content.getBytes(Charset.forName("UTF-8"));
        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
        StorePath path = storageClient.uploadFile(stream, buff.length, fileExtension, null);
        return getResAccessUrl(path);
    }

    /**
     * 返回文件上传成功后的地址名称ַ
     * @param storePath
     * @return
     */
    private String getResAccessUrl(StorePath storePath) {
        String fileUrl = storePath.getFullPath();
        return fileUrl;
    }

    /**
     * 删除文件
     * @param fileUrl
     */
    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.praseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (FdfsUnsupportStorePathException e) {
            e.printStackTrace();
        }
    }

    public String upfileImage(InputStream is, long size, String fileExtName) {
        StorePath path = storageClient.uploadImageAndCrtThumbImage(is, size, fileExtName, null);
        return getResAccessUrl(path);
    }
}
