package com.company;

import java.util.Arrays;
import static com.company.Main.*;

public class Administration {
    static Book[] getBooksForPublishingRetailerID(int publishingRetailerID){

        PublishingRetailer retailer = GetPublishingRetailerById(publishingRetailers, publishingRetailerID);

        if(retailer == null)
            return null;

        Book[] res = new Book[1];

        for(IPublishingArtifact publishingArtifact : retailer.publishingArtifacts){

            if(publishingArtifact instanceof Book)
                if(GetBookById(res, ((Book) publishingArtifact).id) == null){  //daca id-ul cartii nu a aparut
                    if(res[0] == null)
                        res[0] = (Book) publishingArtifact;
                    else {
                        Book[] newBooks = Arrays.copyOf(res, res.length + 1);

                        newBooks[res.length] = (Book) publishingArtifact;
                        res = newBooks;
                    }
                }

            if(publishingArtifact instanceof EditorialGroup){
                for (Book book : ((EditorialGroup) publishingArtifact).books){

                    if(GetBookById(res, book.id) == null){  //daca id-ul cartii nu a aparut
                        if(res[0] == null)
                            res[0] = book;
                        else {
                            Book[] newBooks = Arrays.copyOf(res, res.length + 1);

                            newBooks[res.length] = book;
                            res = newBooks;
                        }
                    }

                }
            }

            if(publishingArtifact instanceof PublishingBrand){
                for (Book book : ((PublishingBrand) publishingArtifact).books){

                    if(GetBookById(res, book.id) == null){  //daca id-ul cartii nu a aparut
                        if(res[0] == null)
                            res[0] = book;
                        else {
                            Book[] newBooks = Arrays.copyOf(res, res.length + 1);

                            newBooks[res.length] = book;
                            res = newBooks;
                        }
                    }

                }
            }
        }

        return res;
    }

    static Language[] getLanguagesForPublishingRetailerID(int publishingReatilerID){
        Book[] carti = getBooksForPublishingRetailerID( publishingReatilerID );
        if(carti == null)
            return null;

        Language[] res = new Language[1];

        for(Book aux : carti)
            if( GetLanguageById(res, aux.languageID) == null){
                if(res[0] == null)
                    res[0] = GetLanguageById(languages, aux.languageID);
                else {
                    Language[] newLanguages = Arrays.copyOf(res, res.length + 1);

                    newLanguages[res.length] = GetLanguageById(languages, aux.languageID);
                    res = newLanguages;
                }
            }

        return res;
    }

    static Country[] getCountriesForBookID(int bookID){
        Country[] res = null;

        for(PublishingRetailer retailer : publishingRetailers) if(retailer != null){
            boolean bookIsInRetailer = false;

            for (IPublishingArtifact artifact : retailer.publishingArtifacts) {
                if (artifact instanceof Book)
                    if (((Book) artifact).id == bookID)
                        bookIsInRetailer = true;

                if (artifact instanceof EditorialGroup)
                    for (Book book : ((EditorialGroup) artifact).books)
                        if (book.id == bookID) {
                            bookIsInRetailer = true;
                            break;
                        }

                if (artifact instanceof PublishingBrand)
                    for (Book book : ((PublishingBrand) artifact).books)
                        if (book.id == bookID) {
                            bookIsInRetailer = true;
                            break;
                        }

                if(bookIsInRetailer)
                    break;
            }

            if(bookIsInRetailer){

                if(res == null)
                    res = retailer.countries;
                else
                    for(Country tara : retailer.countries)
                        if(GetCountryById(res, tara.id) == null){
                            Country[] newTari = Arrays.copyOf(res, res.length + 1);

                            newTari[res.length] = tara;
                            res = newTari;
                        }
            }
        }

        return res;
    }

    static Book[] getAllBooksForRetailerIDs (int retailerID1, int retailerID2){
        Book[] res = getBooksForPublishingRetailerID(retailerID1);
        Book[] books2 = getBooksForPublishingRetailerID(retailerID2);

        if(res == null)
            return books2;
        if(books2 == null)
            return res;

        for(Book aux : books2)
            if(GetBookById(res, aux.id) == null){
                Book[] newBooks = Arrays.copyOf(res, res.length + 1);

                newBooks[res.length] = aux;
                res = newBooks;
            }
        return res;
    }

    static Book[] getCommonBooksForRetailerIDs(int retailerID1, int retailerID2){
        Book[] books1 = getBooksForPublishingRetailerID(retailerID1);
        Book[] books2 = getBooksForPublishingRetailerID(retailerID2);

        if(books1 == null || books2 == null)
            return null;

        Book[] res = null;

        for(Book aux : books1)
            if(GetBookById(books2, aux.id) != null){

                if(res == null) {
                    res = new Book[1];
                    res[0] = aux;
                }
                else {

                    Book[] newBooks = Arrays.copyOf(res, res.length + 1);

                    newBooks[res.length] = aux;
                    res = newBooks;
                }
            }
        return res;
    }

    //Metode auxiliare

    static Book GetBookById(Book[] books, int id){
        //Se da o lista de carti si se cauta cartea cu id-ul dat
        for(Book aux : books) {
            if (aux == null)//am ajuns la finalul listei
                return null;
            if(id == aux.id)
                return aux;
        }
        return null;
    }
    static Author GetAuthorById(Author[] authors, int id){
        for(Author aux : authors) {
            if (aux == null)
                return null;
            if(id == aux.id)
                return aux;
        }
        return null;
    }
    static EditorialGroup GetEditorialGroupById(EditorialGroup[] eG, int id){
        for(EditorialGroup aux : eG) {
            if (aux == null)
                return null;
            if(id == aux.id)
                return aux;
        }
        return null;
    }
    static PublishingBrand GetPublishingBrandById(PublishingBrand[] pB, int id){
        for(PublishingBrand aux : pB) {
            if (aux == null)
                return null;
            if(id == aux.id)
                return aux;
        }
        return null;
    }
    static PublishingRetailer GetPublishingRetailerById(PublishingRetailer[] pR, int id){
        for(PublishingRetailer aux : pR) {
            if (aux == null)
                return null;
            if(id == aux.id)
                return aux;
        }
        return null;
    }
    static Country GetCountryById(Country[] country, int id){
        for(Country aux : country) {
            if (aux == null)
                return null;
            if(id == aux.id)
                return aux;
        }
        return null;
    }
    static Language GetLanguageById(Language[] languages, int id){
        for(Language aux : languages) {
            if (aux == null)
                return null;
            if(id == aux.id)
                return aux;
        }
        return null;
    }
}