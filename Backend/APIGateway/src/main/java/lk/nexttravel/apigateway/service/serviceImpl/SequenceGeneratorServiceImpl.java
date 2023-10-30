package lk.nexttravel.apigateway.service.serviceImpl;


import lk.nexttravel.apigateway.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 30/10/2023
 * Time    : 16:51
 */
@Service
public class SequenceGeneratorServiceImpl implements SequenceGeneratorService {
    @Override
    public long generateSequence(String seqName) {
        return 0;
    }
//    @Autowired
//    private MongoOperations mongoOperations;
//
//    @Autowired
//    public SequenceGeneratorServiceImpl(MongoOperations mongoOperations) {
//
//        this.mongoOperations = mongoOperations;
//    }
//
//    @Override
//    public long generateSequence(String seqName) {
//        DBSequensee counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
//                new Update().inc("seq",1), options().returnNew(true).upsert(true),
//                DBSequensee.class);
//        return !Objects.isNull(counter) ? counter.getSeq() : 1;
//    }
}
