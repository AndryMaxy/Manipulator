package by.akulich.manipulator;

import by.akulich.manipulator.broadcast.BroadcastingServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ManipulatorApplication {

    @Autowired
    private BroadcastingServer server;

    public static void main(String[] args) {
        SpringApplication.run(ManipulatorApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start(){
        new Thread(server).start();
    }

}
