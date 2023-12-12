package com.example.leesplezier.service;

import com.example.leesplezier.dto.ReadingFocusDto;
import com.example.leesplezier.exception.RecordNotFoundException;
import com.example.leesplezier.model.ReadingFocus;
import com.example.leesplezier.repository.ReadingFocusRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReadingFocusService {
    //dependency injection
    private ReadingFocusRepository rfRepos;
//    private final BookRepository bRepos;
//    private final ChildRepository chRepos;

    public ReadingFocusService(ReadingFocusRepository rfRepos) {
        this.rfRepos = rfRepos;

    }
//    public ReadingFocusService(ReadingFocusRepository rfRepos, BookRepository bRepos, ChildRepository chRepos){
//        this.rfRepos = rfRepos;
//        this.bRepos = bRepos;
//        this.chRepos = chRepos;
//    }

    public List<ReadingFocusDto> getAllReadingFoci() {
        List<ReadingFocus> rfList = rfRepos.findAll();

        List<ReadingFocusDto> rfDtos = new ArrayList<>();
        for (ReadingFocus rf : rfList) {
            rfDtos.add(transferToDto(rf));
        }
        return rfDtos;
    }

    public ReadingFocusDto getReadingFocusById(String id){
        if(rfRepos.findById(id).isPresent()){
            ReadingFocus rf = rfRepos.findById(id).get();
            ReadingFocusDto dto = transferToDto(rf);
            return dto;
        }else {
            throw new RecordNotFoundException("No reading focus found, please try again with a different name");
        }

    }

    public List<ReadingFocusDto> getAllReadingFocusByName(String name) {
        List<ReadingFocus> readingFocusList = rfRepos.findByNameContainingIgnoreCase(name);
        return transferRFListToDtoList(readingFocusList);

//        if(readingFocus !=null){
//            ReadingFocusDto readingFocusDto = transferToDto((readingFocus.get()));
//            return readingFocusDto;
//        } else {
//            throw new RecordNotFoundException("No reading focus found");
//        }
    }

    public ReadingFocusDto addReadingFocus(ReadingFocusDto rfDto){
        ReadingFocus rf = transferToRF(rfDto);
        rfRepos.save(rf);

        return transferToDto(rf);
    }

    public void  updateRF(String name, ReadingFocusDto rfDto){
        ReadingFocus rfUpdated = rfRepos.findById(name).orElseThrow(() -> new RecordNotFoundException("No Reading Focus found, please try a different name"));
        rfUpdated.setName(rfDto.getName());
        rfUpdated.setDescription(rfDto.getDescription());
        rfUpdated.setUsefulInfo(rfDto.getUsefulInfo());
        rfRepos.save(rfUpdated);

    }

    //Mapping dto to entity
    public ReadingFocusDto transferToDto(ReadingFocus readingFocus) {
        ReadingFocusDto rfDto = new ReadingFocusDto();

        rfDto.setName(readingFocus.getName());
        rfDto.setDescription(readingFocus.getDescription());
        rfDto.setUsefulInfo(readingFocus.getDescription());

        return rfDto;
    }

    //mapping entity to dto
    public ReadingFocus transferToRF(ReadingFocusDto readingFocusDto) {
        ReadingFocus readingFocus = new ReadingFocus();

        readingFocus.setName(readingFocusDto.getName());
        readingFocus.setDescription(readingFocusDto.getDescription());
        readingFocus.setUsefulInfo(readingFocus.getUsefulInfo());

        return readingFocus;
    }

    public List<ReadingFocusDto> transferRFListToDtoList(List<ReadingFocus> readingFocusList) {
        List<ReadingFocusDto> rfDtoList = new ArrayList<>();

        for (ReadingFocus rf : readingFocusList) {
            ReadingFocusDto dto = transferToDto(rf);

// if necessary add this and dependency injection
            //if(rf.getBooks() !=null){
            // dto.setBookDto(bookService.transferToDto(rf.getBooks()))
            // }
            rfDtoList.add(dto);
        }
        return rfDtoList;
    }

}
