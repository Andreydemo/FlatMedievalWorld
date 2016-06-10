package com.demosoft.testgameserver.rest;

import com.demosoft.testgameserver.map.enity.Point;
import com.demosoft.testgameserver.player.Player;
import com.demosoft.testgameserver.service.Profile;
import com.demosoft.testgameserver.service.entiry.ServiceResponse;
import com.demosoft.testgameserver.update.Event;
import com.demosoft.testgameserver.update.EventManager;
import com.demosoft.testgameserver.update.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by Andrii_Korkoshko on 5/26/2016.
 */
@RestController
@Scope("session")
@RequestMapping("/update")
public class UpdateController {


    @Autowired
    private UpdateService updateService;
    @Autowired
    private EventManager eventManager;
    @Autowired
    private Profile profile;


    @RequestMapping(value = "/move/{diffX}/{diffY}", method = RequestMethod.POST)
    public ServiceResponse<Player> movePlayer(@PathVariable() int diffX, @PathVariable int diffY) {
        ServiceResponse<Player> response = new ServiceResponse<>();
        if (diffX > 1 || diffY > 1 || diffX < -1 || diffY < -1) {
            response.setSuccess(false);
            response.getErrors().add("Location diff is not valid");
            return response;
        }
        updateService.updatePosition(profile.getPlayer(), diffX, diffY);
        response.setSuccess(true);
        response.setObject(profile.getPlayer());
        return response;
    }

    @RequestMapping(value = "/get/{snaphot}", method = RequestMethod.GET)
    public ServiceResponse<List<Event>> getUpdates(@PathVariable long snaphot) {
        ServiceResponse<List<Event>> response = new ServiceResponse<>();
        response.setSuccess(true);
        response.setObject(eventManager.getUpdates(snaphot));
        return response;
    }

}
