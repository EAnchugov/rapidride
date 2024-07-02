package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.ModelMapper;
import com.aston.rapidride.dto.request.ModelRequest;
import com.aston.rapidride.dto.response.ModelResponse;
import com.aston.rapidride.entity.Model;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.ModelRepository;
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
        when(mapper.mapRequestToEntity(request)).thenReturn(model);


        verify(repository).save(model);
    }

    @Test



    }

    @Test


    }

    @Test


        verify(mapper).mapToResponse(model);
    }

    @Test


    }
}
