package com.monilgudhka.questiontagging.model;

import com.monilgudhka.questiontagging.model.Keyword;
import com.monilgudhka.questiontagging.model.Question;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-02T18:02:24")
@StaticMetamodel(CongnitiveLevel.class)
public class CongnitiveLevel_ { 

    public static volatile SingularAttribute<CongnitiveLevel, String> congnitiveLevelName;
    public static volatile SetAttribute<CongnitiveLevel, Question> questionSet;
    public static volatile SingularAttribute<CongnitiveLevel, String> description;
    public static volatile SingularAttribute<CongnitiveLevel, Long> id;
    public static volatile SetAttribute<CongnitiveLevel, Keyword> keywordSet;

}