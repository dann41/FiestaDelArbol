package com.dglapps.fiestadelarbol.repositories.parser;

import com.dglapps.fiestadelarbol.domain.Question;

import java.io.Reader;
import java.util.List;

public interface QuestionParser {

    List<Question> parse(Reader reader);

    List<Question> parse(String content);

    Question parseOne(String content);
}
