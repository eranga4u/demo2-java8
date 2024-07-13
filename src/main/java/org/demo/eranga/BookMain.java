package org.demo.eranga;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class BookMain {

    private static final String BOOKSTORE_XML = "./bookstore-jaxb-actual.xml";

    public static void main(String[] args) throws JAXBException, IOException {

        Bookstore bookstore = getBookstore();

        // create JAXB context and instantiate marshaller
        JAXBContext context = JAXBContext.newInstance(Bookstore.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Write to System.out
        m.marshal(bookstore, System.out);

        // Write to File
        m.marshal(bookstore, new File(BOOKSTORE_XML));

        // get variables from our xml file, created before

        System.out.println("Output from our XML File: ");
        Unmarshaller um = context.createUnmarshaller();
        Bookstore bookstore2 = (Bookstore) um.unmarshal(new FileReader(
            BOOKSTORE_XML));
        List<Book> list = bookstore2.getBooksList();
        for (Book book : list) {
            System.out.println("book.toString() = " + book.toString());
        }
    }

    private static Bookstore getBookstore() {
        ArrayList<Book> bookList = new ArrayList<>();

        // create books
        Book book = new Book();
        book.setIsbn("978-0060554736");
        book.setAuthorName("Neil Strauss");
        book.setAuthorId("A0060554736");
        book.setPublisher("Pearson");
        book.setName("Dhamma Gaweshi");
        bookList.add(book);

        // create bookstore, assigning book
        Bookstore bookstore = new Bookstore();
        bookstore.setName("Colombo Bookstore");
        bookstore.setLocation("Colombo Airport");
        bookstore.setBookList(bookList);
        return bookstore;
    }
}