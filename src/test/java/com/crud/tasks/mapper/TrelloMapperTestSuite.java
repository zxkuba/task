package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    TrelloMapper trelloMapper;

    private TrelloListDto createTrelloListDto(){
        return TrelloListDto.builder()
                .id("1L")
                .name("test")
                .isClosed(false)
                .build();
    }

    private TrelloList createTrelloList(){
        return TrelloList.builder()
                .id("1L")
                .name("test")
                .isClosed(false)
                .build();
    }

    private TrelloCard createTrelloCard(){
        return TrelloCard.builder()
                .name("tets")
                .description("testing mapper")
                .pos("pos")
                .listId("1L")
                .build();
    }

    private TrelloCardDto createTrelloCardDto(){
        return TrelloCardDto.builder()
                .name("test")
                .description("testing mapper")
                .listId("1L")
                .build();
    }



    @Test
    public void TestMapToList(){
        //Given
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(createTrelloListDto());
        //When
        List<TrelloList> testMapToList = trelloMapper.mapToList(trelloListDtoList);
        //Then
        Assert.assertEquals(1, testMapToList.size());
    }

    @Test
    public void TestMapToListDto(){
        //Given
        List<TrelloList> trelloListList = new ArrayList<>();
        trelloListList.add(createTrelloList());
        trelloListList.add(createTrelloList());
        //When
        List<TrelloListDto> testMapToListDto = trelloMapper.mapToListDto(trelloListList);
        //Then
        Assert.assertEquals(2, testMapToListDto.size());
    }

    @Test
    public void TestMapToCardDto(){
        //Given
        TrelloCard trelloCard = createTrelloCard();
        //When
        TrelloCardDto testMapToCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        Assert.assertEquals(trelloCard.getName(), testMapToCardDto.getName());
        Assert.assertEquals(trelloCard.getDescription(), testMapToCardDto.getDescription());
        Assert.assertEquals(trelloCard.getPos(), testMapToCardDto.getPos());
        Assert.assertEquals("1L", testMapToCardDto.getListId());
    }

    @Test
    public void TestMapToCard(){
        //Given
        TrelloCardDto trelloCardDto = createTrelloCardDto();
        //When
        TrelloCard testMapToCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        Assert.assertEquals(testMapToCard.getName(), trelloCardDto.getName());
        Assert.assertEquals(testMapToCard.getDescription(), trelloCardDto.getDescription());
        Assert.assertEquals(testMapToCard.getPos(), trelloCardDto.getPos());
        Assert.assertEquals(testMapToCard.getListId(), trelloCardDto.getListId());

    }

    @Test
    public void TestMapToBoards(){
        //Given
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(createTrelloListDto());

        TrelloBoardDto trelloBoardDto = TrelloBoardDto.builder()
                    .id("1L")
                    .name("testing")
                    .lists(trelloListDtoList)
                    .build();
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);
        //When
        List<TrelloBoard> testMapToBoard = trelloMapper.mapToBoards(trelloBoardDtoList);
        //Then
        Assert.assertEquals(1, testMapToBoard.size());
    }

    @Test
    public void TestMapToBoardsDto(){
        //Given
        List<TrelloList> trelloListList = new ArrayList<>();
        trelloListList.add(createTrelloList());

        TrelloBoard trelloBoard = TrelloBoard.builder()
                .id("1L")
                .name("testing")
                .lists(trelloListList)
                .build();
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard);
        //When
        List<TrelloBoardDto> testMapToBoardDto = trelloMapper.mapToBoardsDto(trelloBoardList);
        //Then
        Assert.assertEquals(1, testMapToBoardDto.size());
    }


}