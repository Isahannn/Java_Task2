package org.example.facade;

import org.example.entity.StraightLineEntity;
import org.example.exception.StraightLineEntityException;
import org.example.io.CustomFileReader;
import org.example.parser.StraightLineParser;
import org.example.service.StraightLineService;
import org.example.service.impl.StraightLineServiceImpl;
import org.example.validator.impl.StraightLineValidatorImpl;

import java.io.IOException;
import java.util.List;

public class StraightLineFacade {
    private static final String FILE_READ_ERROR = "Error while reading file: ";
    private static final String PARSING_ERROR = "Error while parsing lines: ";

    private final StraightLineService lineService = new StraightLineServiceImpl();
    private final CustomFileReader fileReader = new CustomFileReader();
    private final StraightLineValidatorImpl validator = new StraightLineValidatorImpl();
    private final StraightLineParser parser = new StraightLineParser(validator);

    public StraightLineService getLineService() {
        return lineService;
    }

    public List<StraightLineEntity> execute(String filename) throws StraightLineEntityException {
        try {
            List<String> lines = fileReader.readLinesFromFile(filename);
            return parser.parseLines(lines);
        } catch (IOException e) {
            throw new StraightLineEntityException(
                    StraightLineEntityException.ErrorType.IO_ERROR,
                    FILE_READ_ERROR + filename,
                    e
            );
        }
    }

    public List<StraightLineEntity> loadStraightLines(String filename) throws StraightLineEntityException {
        return execute(filename);
    }
}