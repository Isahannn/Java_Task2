package org.example.facade;

import org.example.entity.StraightLineEntity;
import org.example.exception.StraightLineEntityException;
import org.example.io.CustomFileReader;
import org.example.parser.StraightLineParser;
import org.example.service.StraightLineService;
import org.example.service.impl.StraightLineServiceImpl;
import org.example.validator.impl.StraightLineValidatorImpl;

import java.util.List;

public class StraightLineFacade {
    private final StraightLineService lineService = new StraightLineServiceImpl();

    public StraightLineService getLineService() {
        return lineService;
    }

    public List<StraightLineEntity> execute(String filename) throws StraightLineEntityException {
        CustomFileReader fileReader = new CustomFileReader();
        StraightLineValidatorImpl validator = new StraightLineValidatorImpl();
        StraightLineParser parser = new StraightLineParser(validator); // Теперь работает

        List<String> lines = fileReader.readLinesFromFile(filename);
        return parser.parseLines(lines);
    }

    public List<StraightLineEntity> loadStraightLines(String filename) throws StraightLineEntityException {
        return execute(filename);
    }
}