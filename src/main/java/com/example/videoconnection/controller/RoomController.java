package com.example.videoconnection.controller;

import com.example.videoconnection.model.Room;
import com.example.videoconnection.repository.RoomRepository;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @PostMapping("/create")
    public String createRoom(Model model) {
        Room room = new Room();
        room.setRoomCode(generateRoomCode());
        roomRepository.save(room);
        model.addAttribute("room", room);
        System.out.println("Created Room Code: " + room.getRoomCode());
        return "room";
    }



    @GetMapping("/join")
    public String joinRoom(@RequestParam("roomCode") String roomCode, Model model) {
        Room room = roomRepository.findByRoomCode(roomCode);

        if (room == null) {
            model.addAttribute("error", "Room not found");
            return "index";
        }

        model.addAttribute("room", room);
        return "room";
    }

    private String generateRoomCode() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
