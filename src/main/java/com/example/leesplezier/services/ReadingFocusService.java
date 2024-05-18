package com.example.leesplezier.services;

import com.example.leesplezier.dtos.ReadingFocusDto;
import com.example.leesplezier.exceptions.RecordNotFoundException;
import com.example.leesplezier.models.ReadingFocus;
import com.example.leesplezier.repositories.ReadingFocusRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReadingFocusService {
    //dependency injection
    private ReadingFocusRepository rfRepos;

    public ReadingFocusService(ReadingFocusRepository rfRepos) {
        this.rfRepos = rfRepos;
    }

    public List<ReadingFocusDto> getAllReadingFoci() {
        Iterable<ReadingFocus> rfList = rfRepos.findAll();

        List<ReadingFocusDto> rfDtos = new ArrayList<>();
        for (ReadingFocus rf : rfList) {
            rfDtos.add(transferToDto(rf));
        }
        return rfDtos;
    }

    public ReadingFocusDto getReadingFocusByName(String id){

        var readingFocus = rfRepos.findByNameContainingIgnoreCase(id);

        if(readingFocus !=null){
            ReadingFocusDto readingFocusDto = transferToDto(readingFocus);
            return readingFocusDto;
        }else {
            throw new RecordNotFoundException("No reading focus found with that name, please try again with a different name");
        }
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

    public void deleteReadingFocus(String name) {
        rfRepos.deleteById(name);
    }

    //Mapping dto to entity
    public static ReadingFocusDto transferToDto(ReadingFocus readingFocus) {
        ReadingFocusDto rfDto = new ReadingFocusDto();

        rfDto.setName(readingFocus.getName());
        rfDto.setDescription(readingFocus.getDescription());
        rfDto.setUsefulInfo(readingFocus.getUsefulInfo());

        return rfDto;
    }

    //mapping entity to dto
    public ReadingFocus transferToRF(ReadingFocusDto readingFocusDto) {
        ReadingFocus readingFocus = new ReadingFocus();

        readingFocus.setName(readingFocusDto.getName());
        readingFocus.setDescription(readingFocusDto.getDescription());
        readingFocus.setUsefulInfo(readingFocusDto.getUsefulInfo());
        rfRepos.save(readingFocus);

        return readingFocus;
    }

    public static List<ReadingFocusDto> transferRFListToDtoList(List<ReadingFocus> readingFocusList) {
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
