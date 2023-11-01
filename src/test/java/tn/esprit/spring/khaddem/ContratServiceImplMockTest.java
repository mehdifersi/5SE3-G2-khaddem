package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.services.ContratServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ContratServiceImplMockTest {
    @InjectMocks
    private ContratServiceImpl contratService;
    @Mock
    private ContratRepository contratRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void retreiveAllUniversitiesMock(){

    }

}
