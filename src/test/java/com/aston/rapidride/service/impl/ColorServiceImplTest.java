package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.ColorMapper;
import com.aston.rapidride.dto.request.ColorRequest;
import com.aston.rapidride.dto.response.ColorResponse;
import com.aston.rapidride.entity.Color;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.ColorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


    @Mock

    @Mock

    @InjectMocks

    @BeforeEach


    }

    @Test
        when(mapper.mapRequestToEntity(request)).thenReturn(color);


        verify(repository).save(color);
    }

    @Test



    }

    @Test


    }

    @Test


        verify(mapper).mapToResponse(color);
    }

    @Test


    }
}
