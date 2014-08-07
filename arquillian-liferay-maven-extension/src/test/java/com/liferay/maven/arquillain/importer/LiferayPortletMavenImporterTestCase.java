
package com.liferay.maven.arquillain.importer;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.archive.importer.MavenImporter;

public class LiferayPortletMavenImporterTestCase {

    @Before
    public void cleanTarget() throws IOException {
        new File("src/it/demo-portlet/target").delete();

    }

    @Test
    public void importWar() {
        // When
        final WebArchive archive =
            doImport("src/it/demo-portlet/pom.xml");

        // Then
        assertNotNull(archive.get(ArchivePaths.create(
            "/WEB-INF/lib", "util-java.jar")));
        assertNotNull(archive.get(ArchivePaths.create(
            "/WEB-INF/lib", "util-taglib.jar")));
        assertNotNull(archive.get(ArchivePaths.create(
            "/WEB-INF/lib", "commons-logging.jar")));
        assertNotNull(archive.get(ArchivePaths.create(
            "/WEB-INF/lib", "log4j-extras.jar")));
        assertNotNull(archive.get(ArchivePaths.create(
            "/WEB-INF", "liferay-portlet.xml")));
        assertNotNull(archive.get(ArchivePaths.create(
            "/WEB-INF/classes", "log4j.properties")));
        assertNotNull(archive.get(ArchivePaths.create("/WEB-INF/tld", "aui.tld")));
        assertNotNull(archive.get(ArchivePaths.create(
            "/WEB-INF/tld", "liferay-portlet.tld")));
        assertNotNull(archive.get(ArchivePaths.create(
            "/WEB-INF/jsp", "_servlet_context_include.jsp")));

    }

    private WebArchive doImport(String pomFile) {

        try {

            // When
            WebArchive archive =
                ShrinkWrap.create(MavenImporter.class).loadPomFromFile(pomFile).importBuildOutput()
                    .as(WebArchive.class);

            return archive;
        }
        catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }
        return null;
    }
}
