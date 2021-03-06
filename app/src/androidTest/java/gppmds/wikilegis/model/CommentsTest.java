package gppmds.wikilegis.model;

import org.junit.Test;

import gppmds.wikilegis.exception.CommentsException;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;



public class CommentsTest {

    @Test
    public void testCreateCommentsTest(){
        boolean isValid = true;

        try{
            Comments comments = new Comments(22,22,"Date","ContentType",9,"Comment");
        }catch (CommentsException commentsException){
            isValid = false;
        }
        assertTrue(isValid);
    }

    @Test

    public void testNullId(){
        boolean isValid = true;

        try{
            Comments comments = new Comments(null,22,"Date","ContentType",9,"Comment");
        }catch (CommentsException commentsException){
            isValid = false;
        }

        assertFalse(isValid);
    }

    @Test

    public  void testNullIdUser(){
        boolean isValid = true;

        try{
            Comments comments = new Comments(22,null,"Date","ContentType",9,"Comment");
        }catch (CommentsException commentsException){
            isValid = false;
        }

        assertFalse(isValid);
    }

    @Test

    public void testNullDate(){
        boolean isValid = true;

        try{
            Comments comments = new Comments(22,22,null,"ContentType",9,"Comment");
        }catch (CommentsException commentsException){
            isValid = false;
        }
        assertFalse(isValid);
    }

    @Test

    public void testEmptyDate(){
        boolean isValid = true;

        try{
            Comments comments = new Comments(22,22,"","ContentType",9,"Comment");
        }catch (CommentsException commentsException){
            isValid = false;
        }

        assertFalse(isValid);
    }

    @Test
    public void testDateOnlyWithSpace(){
        boolean isValid = true;

        try{
            Comments comments = new Comments(22,22,"   ","ContentType",9,"Comment");
        }catch (CommentsException commentsException){
            isValid = false;
        }

        assertFalse(isValid);
    }

    @Test

    public void testNullContentType(){
        boolean isValid = true;

        try{
            Comments comments = new Comments(22,22,"Date",null,9,"Comment");
        } catch (CommentsException commentsExeption){
            isValid = false;
        }
        assertFalse(isValid);
    }

    @Test

    public void testEmptyContentType(){
        boolean isValid = true;

        try{
            Comments comments = new Comments(22,22,"Date","",9,"Comment");
        }catch (CommentsException commentsException){
            isValid = false;
        }

        assertFalse(isValid);
    }
    @Test
    public void testCommentTypeOnlyWithSpace(){
        boolean isValid = true;

        try{
            Comments comments = new Comments(22,22,"Date","   ",9,"Comment");
        }catch (CommentsException commentsException){
            isValid = false;
        }

        assertFalse(isValid);
    }
    @Test

    public void testNullObjectPk(){
        boolean isValid = true;

        try{
            Comments comments = new Comments(22,22,"Date","ContentType",null,"Comment");
        }catch (CommentsException commentsException){
            isValid  = false;
        }

        assertFalse(isValid);
    }

    @Test

    public void testNullComment(){
        boolean isValid = true;

        try{
            Comments comments = new Comments(22,22,"Date","ContentType",9,null);
        }catch (CommentsException commentsException){
            isValid = false;
        }

        assertFalse(isValid);
    }

    @Test

    public void testEmptyComment(){
        boolean isValid = true;

        try{
            Comments comments = new Comments(22,22,"Date","ContentType",9,"");
        }catch (CommentsException commentsException){
            isValid = false;
        }

        assertFalse(isValid);
    }
    @Test
    public void testCommentOnlyWithSpace(){
        boolean isValid = true;

        try{
            Comments comments = new Comments(22,22,"Date","ContentType",9,"   ");
        }catch (CommentsException commentsException){
            isValid = false;
        }

        assertFalse(isValid);
    }
}
