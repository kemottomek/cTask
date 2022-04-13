package com.cisco.app.service;

import com.cisco.app.constroller.Converter;
import com.cisco.app.respository.CaseEntityRepository;
import com.cisco.app.respository.NoteEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/*
 * @author nbtwszol
 */
@ExtendWith(MockitoExtension.class)
public class CaseServiceTest {

    private final NoteEntityRepository noteEntityRepository = Mockito.mock(NoteEntityRepository.class);
    private final CaseEntityRepository caseEntityRepository = Mockito.mock(CaseEntityRepository.class);
    private final Converter converter = Mockito.mock(Converter.class);

    @InjectMocks
    private CaseService caseService;

    @Test
    public void test_Case_by_Status() {

    }

    @Test
    public void test_Case_by_CaseId() {

    }

    @Test
    public void test_Case_by_UserId() {

    }

    @Test
    public void test_add_Case() {

    }

    @Test
    public void test_add_Note() {

    }
}
