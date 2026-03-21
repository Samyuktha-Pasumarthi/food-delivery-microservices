package com.Samyuktha.order.service;


import com.Samyuktha.order.entity.Sequence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static com.mongodb.client.model.Filters.eq;
import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
    public class SequenceGeneratorTest {

        @InjectMocks
        private SequenceGenerator sequenceGenerator;

        @Mock
        MongoOperations mongoOperations;

    @Test
    void testGenerateNextOrderId() {

        Sequence mockSequence = new Sequence();
        mockSequence.setSequence(5);

        when(mongoOperations.findAndModify(
                any(Query.class),
                any(Update.class),
                any(FindAndModifyOptions.class),
                eq(Sequence.class)
        )).thenReturn(mockSequence);

        int result = sequenceGenerator.generateNextOrderId();

        assertEquals(5, result);
    }
    }

