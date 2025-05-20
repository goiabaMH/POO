package br.ifs.tdd.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ EstoqueTest.class, LoteTest.class, ProdutoTest.class })
public class AllTests {

}
