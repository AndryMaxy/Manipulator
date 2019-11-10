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
    public ResponseEntity move(@RequestParam int x, @RequestParam int y) throws Exception {
        Robot robot = new Robot();
        robot.mouseMove(x, y);

        return ResponseEntity.ok("gg");
    }
}
