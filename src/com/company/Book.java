package com.company;

import java.util.Arrays;

public class Book implements IPublishingArtifact {
    int id ;
    String name;
    String subtitle;
    String isbn;
    int pageCount;
    String keyWords;
    int languageID;
    String createdOn;

    Author[] authors;

    public Book(int id, String name, String subtitle, String isbn, int pageCount, String keyWords, int languageID, String createdOn) {
        this.id = id;
        this.name = name;
        this.subtitle = subtitle;
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.keyWords = keyWords;
        this.languageID = languageID;
        this.createdOn = createdOn;

        authors = new Author[1];
    }

    public void addAuthors(Author author) {
        if(this.authors[0] == null)
            this.authors[0] = author;
        else{
            Author[] newAuthorss = Arrays.copyOf(this.authors, this.authors.length + 1);

            newAuthorss[this.authors.length] = author;
            this.authors = newAuthorss;
        }
    }

    @Override
    public void Publish() {
        System.out.println("<xml>");
        System.out.println("\t<title>" + name + "</title>");
        System.out.println("\t<id>" + id + "</id>");
        if(subtitle != null)
            System.out.println("\t<subtitle>" + subtitle + "</subtitle>");
        System.out.println("\t<isbn>" + isbn + "</isbn>");
        System.out.println("\t<pageCount>" + pageCount + "</pageCount>");
        System.out.println("\t<keywords>" + keyWords + "</keywords>");
        System.out.println("\t<languageID>" + languageID + "</languageID>");
        System.out.println("\t<createdOn>" + createdOn + "</createdOn>");


        System.out.print("\t<authors>");
        for (Author aux : authors) {
            System.out.print(" " + aux.toString() + " ");
        }
        System.out.println("</authors>");

        System.out.println("</xml>");
    }
}
class PublishingBrand implements IPublishingArtifact{
    int id;
    String name;
    Book[] books;

    public PublishingBrand(int id, String name) {
        this.id = id;
        this.name = name;

        this.books = new Book[1];
    }

    public void addBook(Book book) {
        if(this.books[0] == null)
            this.books[0] = book;
        else{
            Book[] newBooks = Arrays.copyOf(this.books, this.books.length + 1);

            newBooks[this.books.length] = book;
            this.books = newBooks;
        }
    }

    @Override
    public void Publish() {
        System.out.println("<xml>");
        System.out.println("\t<publishingBrand>");
        System.out.println("\t\t<ID>" + id + "<ID>");
        System.out.println("\t\t<Name>" + name + "<Name>");
        System.out.println("\t</publishingBrand>");
        System.out.println("\t<books>");
        for(Book aux : books){
            System.out.println("\t\t<book>");

            System.out.println("\t\t\t<title>" + aux.name + "</title>");
            if(aux.subtitle != null)
                System.out.println("\t\t\t<subtitle>" + aux.subtitle + "</subtitle>");
            System.out.println("\t\t\t<isbn>" + aux.isbn + "</isbn>");
            System.out.println("\t\t\t<pageCount>" + aux.pageCount + "</pageCount>");
            System.out.println("\t\t\t<keywords>" + aux.keyWords + "</keywords>");
            System.out.println("\t\t\t<languageID>" + aux.languageID + "</languageID>");
            System.out.println("\t\t\t<createdOn>" + aux.createdOn + "</createdOn>");


            System.out.print("\t\t\t<authors>");
            for (Author auxAutor : aux.authors) {
                System.out.print(" " + auxAutor.toString() + " ");
            }
            System.out.print("</authors>\n");

            System.out.println("\t\t</book>");
        }
        System.out.println("\t</books>");
        System.out.println("</xml>");
    }
}
class EditorialGroup implements IPublishingArtifact{
    int id;
    String name;
    Book[] books;

    public EditorialGroup(int id, String name) {
        this.id = id;
        this.name = name;

        this.books = new Book[1];
    }
    public void addBook(Book book) {
        if(this.books[0] == null)
            this.books[0] = book;
        else{
            Book[] newBooks = Arrays.copyOf(this.books, this.books.length + 1);

            newBooks[this.books.length] = book;
            this.books = newBooks;
        }
    }

    @Override
    public void Publish() {
        System.out.println("<xml>");
        System.out.println("\t<editorialGroup>");
        System.out.println("\t\t<ID>" + id + "<ID>");
        System.out.println("\t\t<Name>" + name + "<Name>");
        System.out.println("\t</editorialGroup>");
        System.out.println("\t<books>");
        for(Book aux : books){
            System.out.println("\t\t<book>");

            System.out.println("\t\t\t<title>" + aux.name + "</title>");
            if(aux.subtitle != null)
                System.out.println("\t\t\t<subtitle>" + aux.subtitle + "</subtitle>");
            System.out.println("\t\t\t<isbn>" + aux.isbn + "</isbn>");
            System.out.println("\t\t\t<pageCount>" + aux.pageCount + "</pageCount>");
            System.out.print("\t\t\t<keywords>" + aux.keyWords + "</keywords>");
            System.out.println("\t\t\t<languageID>" + aux.languageID + "</languageID>");
            System.out.println("\t\t\t<createdOn>" + aux.createdOn + "</createdOn>");


            System.out.print("\t\t\t<authors>");
            for (Author auxAutor : aux.authors) {
                System.out.print(" " + auxAutor.toString() + " ");
            }
            System.out.print("</authors>\n");

            System.out.println("\t\t</book>");
        }
        System.out.println("\t</books>");
        System.out.println("</xml>");
    }
}
class PublishingRetailer{
    int id;
    String name;

    IPublishingArtifact[] publishingArtifacts;
    Country[] countries;

    public PublishingRetailer(int id, String name) {
        this.id = id;
        this.name = name;

        this.countries = new Country[1];
        this.publishingArtifacts = new IPublishingArtifact[1];
    }

    public void addCountry(Country country) {
        if(this.countries[0] == null)
            this.countries[0] = country;
        else{
            Country[] newCountries = Arrays.copyOf(this.countries, this.countries.length + 1);

            newCountries[this.countries.length] = country;
            this.countries = newCountries;
        }
    }
    public void addPublishingArtifacts(IPublishingArtifact publishingArtifact) {
        if (this.publishingArtifacts[0] == null)
            this.publishingArtifacts[0] = publishingArtifact;
        else{
            IPublishingArtifact[] newPublishingArtifacts = Arrays.copyOf(this.publishingArtifacts, this.publishingArtifacts.length + 1);

            newPublishingArtifacts[this.publishingArtifacts.length] = publishingArtifact;
            this.publishingArtifacts = newPublishingArtifacts;
        }
    }
}

class Author{
    int id;
    String firstName;
    String lastName;

    public Author(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toString() {
        if(firstName != null)
            return "<author> " + id + " " + firstName + " " + lastName + " </author>";
        return id + lastName;
    }
}
class Language{
    int id;
    String code;
    String name;

    public Language(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
}
class Country{
    int id;
    String countryCode;

    public Country(int id, String countryCode) {
        this.id = id;
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}

interface IPublishingArtifact{
    void Publish();
}