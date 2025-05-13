package org.example.facade;

import org.example.entity.StraightLineEntity;
import org.example.exception.StraightLineEntityException;
import org.example.io.CustomFileReader;
import org.example.parser.StraightLineParser;
import org.example.validator.impl.StraightLineValidatorImpl;

import java.util.List;

public class StraightLineFacade {
    private final CustomFileReader fileReader = new CustomFileReader();
    private final StraightLineParser parser = new StraightLineParser(new StraightLineValidatorImpl());

    public List<StraightLineEntity> loadStraightLines(String filename) throws StraightLineEntityException {
        try {
            List<String> lines = fileReader.readLinesFromFile(filename);  // Метод, который выбрасывает StraightLineEntityException
            return parser.parseLines(lines);
        } catch (StraightLineEntityException e) {  // Только this exception
            throw new StraightLineEntityException(
                    StraightLineEntityException.ErrorType.IO_ERROR,
                    "Error while reading file: " + filename,
                    e
            );
        }
    }
}
