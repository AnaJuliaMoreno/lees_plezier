package com.example.leesplezier.service;

import com.example.leesplezier.dto.ReadingFocusDto;
import com.example.leesplezier.model.ReadingFocus;
import com.example.leesplezier.repository.BookRepository;
import com.example.leesplezier.repository.ChildRepository;
import com.example.leesplezier.repository.ReadingFocusRepository;
import org.springframework.stereotype.Service;

@Service
public class ReadingFocusService {
    //dependency injection
    private ReadingFocusRepository rfRepos;
    private final BookRepository bRepos;
    private final ChildRepository chRepos;

    public ReadingFocusService(ReadingFocusRepository rfRepos, BookRepository bRepos, ChildRepository chRepos){
        this.rfRepos = rfRepos;
        this.bRepos = bRepos;
        this.chRepos = chRepos;
    }

    //Mapping dto to entity
    public ReadingFocusDto transferToDto(ReadingFocus readingFocus){
        ReadingFocusDto rfDto = new ReadingFocusDto();

        rfDto.setName(readingFocus.getName());
        rfDto.setDescription(readingFocus.getDescription());
        rfDto.setUsefulInfo(readingFocus.getDescription());

        return rfDto;
    }
    //mapping entity to dto
    public ReadingFocus transferToRF(ReadingFocusDto readingFocusDto){
        ReadingFocus readingFocus = new ReadingFocus();

        readingFocus.setName(readingFocusDto.getName());
        readingFocus.setDescription(readingFocusDto.getDescription());
        readingFocus.setUsefulInfo(readingFocus.getUsefulInfo());

        return readingFocus;
    }

}
