package com.hei.wallet.wallety.repository;

import com.hei.wallet.wallety.fjpa.FJPARepository;
import com.hei.wallet.wallety.fjpa.StatementWrapper;
import com.hei.wallet.wallety.model.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository extends FJPARepository<Category> {
    public CategoryRepository(StatementWrapper statementWrapper) {
        super(Category.class, statementWrapper);
    }
}
