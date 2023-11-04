package lk.nexttravel.guide_service.service.impl;

import lk.nexttravel.guide_service.entity.util.DatabaseSequence;
import lk.nexttravel.guide_service.persistence.GuideRepository;
import lk.nexttravel.guide_service.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 10:48
 */
@Service
public class SequenceGeneratorServiceImpl implements SequenceGeneratorService {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    public SequenceGeneratorServiceImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }



    @Override
    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;

    }
}
