package com.monilgudhka.questiontagging.model;

import com.monilgudhka.questiontagging.model.CongnitiveLevel;
import com.monilgudhka.questiontagging.model.QuestionType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-02T18:02:24")
@StaticMetamodel(Keyword.class)
public class Keyword_ { 

    public static volatile SingularAttribute<Keyword, CongnitiveLevel> congnitiveLevel;
    public static volatile SingularAttribute<Keyword, Long> id;
    public static volatile SingularAttribute<Keyword, String> word;
    public static volatile SingularAttribute<Keyword, QuestionType> questionType;

}