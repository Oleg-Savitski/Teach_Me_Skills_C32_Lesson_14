package com.teachmeskills.lesson_14.app;

import com.teachmeskills.lesson_14.exception.InvalidDocumentException;
import com.teachmeskills.lesson_14.validator.DocumentProcessor;

import java.io.IOException;
import java.util.Scanner;

/**
 * The document number is a string consisting of letters and numbers (without service characters).
 * Let this file contain each document number from a new line and no other information
 * in the line, only the document number.
 * A valid document number must be 15 characters long and begin with the sequence "docnum"
 * (hereinafter any sequence of letters/numbers) or "contract" (hereinafter any sequence of letters/numbers).
 * Write a program to read information from an input file - the path to the input
 * file must be set via the console.
 * The program should check the document numbers for validity.
 * Valid "docnum" document numbers should be recorded in a single report file.
 * Valid "contract" numbers should be recorded in another report file.
 * Invalid document numbers should be written to another report file,
 * but after the document numbers, you should add information about why this document
 * is invalid (incorrect sequence of characters at the beginning / there are
 * service characters in the document name and more).
 *
 * The path to the directory where the document file is stored:
 * src/com/teachmeskills/lesson_14/resource/document/doc.txt
 */
public class ApplicationRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputFilePath;

        while (true) {
            System.out.print("Enter the path to the file: "); // src/com/teachmeskills/lesson_14/resource/document/doc.txt
            inputFilePath = scanner.nextLine().trim();

            if (inputFilePath.isEmpty()) {
                System.out.println("The file path cannot be empty. Please try again.");
            } else {
                break;
            }
        }

        DocumentProcessor processor = new DocumentProcessor();

        try {
            processor.processDocuments(inputFilePath);
            System.out.println("Document processing completed successfully.\nReports have been created.");
        } catch (IOException e) {
            System.out.println("Error processing the file: " + e.getMessage());
        } catch (InvalidDocumentException e) {
            System.out.println("Document validation error: " + e.getMessage());
            System.out.println("Invalid document: " + e.getDocument() + " (Error Code: " + e.getErrorCode() + ")");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}