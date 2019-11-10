package by.akulich.manipulator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Slf4j
@Controller
@RequestMapping("/mouse")
public class MouseController {

    @PostMapping(value = "/move", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String move(@RequestParam int x, @RequestParam int y) throws Exception {
        log.info("move invoked");
        Robot robot = new Robot();
        Point mouse = MouseInfo.getPointerInfo().getLocation();
        int currentX = (int) mouse.getX();
        int currentY = (int) mouse.getY();
        log.info(String.format("currentX %d    ---    currentY %d", currentX, currentY));
        robot.mouseMove(x * 2 + currentX, y * 2 + currentY);
        log.info("move performed");
        return "gg";
    }
}
