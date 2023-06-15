package com.groupshow.artwork.painting;

import com.groupshow.user.User;
import com.groupshow.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/painting")
@CrossOrigin(origins = "*")
public class PaintingController {

    @Autowired
    private PaintingService paintingService;

    @PostMapping("/upload")
    public ResponseEntity<Boolean> uploadPainting(@RequestBody PaintingDto paintingDto) {
        return ResponseEntity.ok(paintingService.uploadPainting(paintingDto));
    }
}
