package com.example.member2.service;

import com.example.member2.entity.Board;
import com.example.member2.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepositoy;

    public void write(Board board, MultipartFile file) throws Exception{

        String projectPath = System.getProperty("user.dir")+"/src/main/resources/static/files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid+"_"+file.getOriginalFilename();

        File saveFile = new File(projectPath,fileName);

        file.transferTo(saveFile);

        board.setFilename(fileName);
        board.setFilepath("/files/"+fileName);

        boardRepositoy.save(board);
    }

    public Page<Board> boardList(Pageable pageable){
        return boardRepositoy.findAll(pageable);
    }

    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {

        return boardRepositoy.findByTitleContaining(searchKeyword, pageable);
    }
    public Board boardView(Integer id){
        return boardRepositoy.findById(id).get();
    }

    public void boardDelete(Integer id){
        boardRepositoy.deleteById(id);
    }
}
