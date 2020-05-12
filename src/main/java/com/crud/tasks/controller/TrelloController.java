package com.crud.tasks.controller;


import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;


    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards(){
       List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
           trelloBoards.stream()
                   .filter(w->w.getName()!=null)
                   .filter(k->k.getId()!=null)
                   .filter(s->s.getName().contains("Kodilla"))
                   .forEach(trelloBoardDto ->{
                       System.out.println(trelloBoardDto.getId());
                       trelloBoardDto.getLists().forEach(trelloListDto ->
                               System.out.println(trelloListDto.getName()+" - "+trelloListDto.getId()+" - "+trelloListDto.isClosed()));
                   });

   //List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
           // trelloBoards.forEach(trelloBoardDto -> {
                //System.out.println(trelloBoardDto.getName() + "-" + trelloBoardDto.getId());

                //System.out.println("This board contains lists: ");
                //trelloBoardDto.getLists().forEach(trelloList ->
                      //  System.out.println(trelloList.getName() + "-" + trelloList.getId()+"-"+trelloList.isClosed()));
           // });
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloClient.createNewCard(trelloCardDto);
    }

}
