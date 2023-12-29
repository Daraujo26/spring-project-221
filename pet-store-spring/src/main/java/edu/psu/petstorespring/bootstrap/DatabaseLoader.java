package edu.psu.petstorespring.bootstrap;

import edu.psu.petstorespring.model.Pet;
import edu.psu.petstorespring.repository.impl.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private PetRepository petRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event){
        System.out.println("Database started");

    }
}
