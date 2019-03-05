package com.cstins.front.api;

import com.cstins.front.entity.Link;
import com.cstins.front.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: csti-ns
 * @description: 友情链接相关API
 * @author: 杨云龙
 **/

@RestController
public class LinkAPI {

    @Autowired
    private LinkService linkService;

    @GetMapping("/links")
    public List<Link> getLinks() {
        return linkService.getLinks();
    }

    @PostMapping("/link")
    public boolean addLink(@RequestBody Link link) {
        if (link == null) return false;
        return linkService.addLink(link);
    }

    @PutMapping("/link")
    public boolean updateLink(@RequestBody Link link) {
        if (link == null) return false;
        return linkService.updateLink(link);
    }

    /**
     *  {
     *      "id":78,
     *      "link_addr": "a",
     *      "link_name": "a",
     *      "link_type": "FRIEND"
     *   }
     * @param link
     * @return
     */
    @DeleteMapping("/link")
    public boolean delLink(@RequestBody Link link) {
        if (link == null) return false;
        return linkService.delLnk(link);
    }
}
