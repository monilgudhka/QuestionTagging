package com.monilgudhka.questiontagging.model;

import com.monilgudhka.questiontagging.model.Concept;
import com.monilgudhka.questiontagging.model.QuestionBank;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-02T18:02:24")
@StaticMetamodel(DomainInfo.class)
public class DomainInfo_ { 

    public static volatile SetAttribute<DomainInfo, QuestionBank> questionBankSet;
    public static volatile SingularAttribute<DomainInfo, String> domainName;
    public static volatile SingularAttribute<DomainInfo, String> location;
    public static volatile SingularAttribute<DomainInfo, Long> id;
    public static volatile SetAttribute<DomainInfo, Concept> conceptSet;
    public static volatile SingularAttribute<DomainInfo, Double> version;

}