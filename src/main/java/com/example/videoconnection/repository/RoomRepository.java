package com.example.videoconnection.repository;

import com.example.videoconnection.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByRoomCode(String roomCode);
}
