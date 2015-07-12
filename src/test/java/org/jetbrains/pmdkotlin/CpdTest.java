package org.jetbrains.pmdkotlin;

import net.sourceforge.pmd.cpd.CPD;
import net.sourceforge.pmd.cpd.CPDConfiguration;
import net.sourceforge.pmd.cpd.CPDListener;
import org.jetbrains.pmdkotlin.cpd.KotlinLanguage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.mock;

public class CpdTest {

    private CPD cpd;
    private CPDConfiguration config;
    private CPDListener listener;

    @BeforeTest
    public void setUp() {
        config = new CPDConfiguration();
        config.setLanguage(new KotlinLanguage());
        config.setEncoding("UTF-8");
        cpd = new CPD(config);
        listener = mock(CPDListener.class);
        cpd.setCpdListener(listener);
    }

    @Test
    public void testDuplicateFunction() throws IOException {
        cpd.add(getKotlinFile("DuplicateFunction.kt"));
        cpd.go();
    }


    private File getKotlinFile(String name) {
        try {
            return new File(getClass().getResource(name).toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Can't find " + name, e);
        }
    }
}
