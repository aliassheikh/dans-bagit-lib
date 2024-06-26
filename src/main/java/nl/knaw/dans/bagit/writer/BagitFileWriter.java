/*
 * Copyright (C) 2023 DANS - Data Archiving and Networked Services (info@dans.knaw.nl)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.knaw.dans.bagit.writer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.knaw.dans.bagit.domain.Version;
import java.io.BufferedWriter;

/**
 * Responsible for writing the bagit.txt to the filesystem
 */
public final class BagitFileWriter {
  private static final Logger logger = LoggerFactory.getLogger(BagitFileWriter.class);
  private static final ResourceBundle messages = ResourceBundle.getBundle("MessageBundle");
  
  private BagitFileWriter(){
    //intentionally left empty
  }
  
  /**
   * Write the bagit.txt file in required UTF-8 encoding.
   * 
   * @param version the version of the bag to write out
   * @param encoding the encoding of the tag files
   * @param outputDir the root of the bag
   * 
   * @throws IOException if there was a problem writing the file
   */
  public static void writeBagitFile(final Version version, final Charset encoding, final Path outputDir) throws IOException{
    final Path bagitPath = outputDir.resolve("bagit.txt");
    logger.debug(messages.getString("write_bagit_file_to_path"), outputDir);

    try (BufferedWriter writer = Files.newBufferedWriter(bagitPath,
          StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE)) {

      final String firstLine = "BagIt-Version: " + version + System.lineSeparator();
      logger.debug(messages.getString("writing_line_to_file"), firstLine, bagitPath);
      writer.append(firstLine);

      final String secondLine = "Tag-File-Character-Encoding: " + encoding + System.lineSeparator();
      logger.debug(messages.getString("writing_line_to_file"), secondLine, bagitPath);
      writer.append(secondLine);
    }
  }
}
