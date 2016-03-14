import org.testng.annotations.Test;

import static junit.framework.Assert.*;


public class BenderTest {
    @Test
    public void test01() {
        String mapa = "" +
                "#######\n" +
                "# X   #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "# $   #\n" +
                "#     #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SSSS", bender.run());
    }

    @Test
    public void test02() {
        String mapa = "" +
                "#######\n" +
                "# X   #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#   $ #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SSSSSEE", bender.run());
    }

    @Test
    public void test03() {
        String mapa = "" +
                "#######\n" +
                "#     #\n" +
                "#     #\n" +
                "#    $#\n" +
                "#     #\n" +
                "# X   #\n" +
                "#     #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEEENNN", bender.run());
    }

    @Test
    public void test04() {
        String mapa = "" +
                "#######\n" +
                "#    $#\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#  X  #\n" +
                "#     #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEENNNNN", bender.run());
    }


    @Test
    public void test05() {
        String mapa = "" +
                "#######\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#  X###\n" +
                "#$    #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEEWWWW", bender.run());
    }

    @Test
    public void test25() {
        String mapa = "" +
                "#######\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#  X###\n" +
                "#$ T  #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEEWWWW", bender.run());
    }

    @Test
    public void test26() {
        String mapa = "" +
                "#######\n" +
                "#   # #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#$  #X#\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("El robot a entrado en bucle", bender.run());
    }

    @Test
    public void test24() {
        String mapa = "" +
                "#######\n" +
                "#    I#\n" +
                "#     #\n" +
                "#   X #\n" +
                "#I    #\n" +
                "#     #\n" +
                "#  $  #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SSSENNNNNWWWWSSSSSEE", bender.run());
    }


    @Test
    public void test06() {
        String mapa = "" +
                "###########\n" +
                "#T        #\n" +
                "#  ########\n" +
                "#$        #\n" +
                "#   X     #\n" +
                "# #####  T#\n" +
                "#       ###\n" +
                "#         #\n" +
                "###########";
        Bender bender = new Bender(mapa);
        assertEquals("EEEEESSS", bender.run());
    }

    @Test
    public void test07() {
        String mapa = "" +
                "###########\n" +
                "#T       $#\n" +
                "#  ########\n" +
                "#         #\n" +
                "#   X     #\n" +
                "#         #\n" +
                "#       ###\n" +
                "#     T   #\n" +
                "###########";
        Bender bender = new Bender(mapa);
        assertEquals("SSSEEEEEEEEEE", bender.run());
    }


    @Test
    public void test08() {
        String mapa = "" +
                "###########\n" +
                "######    #\n" +
                "#$   #    #\n" +
                "#### #    #\n" +
                "#  # #    #\n" +
                "#  #X#    #\n" +
                "#  #I#    #\n" +
                "#  ###    #\n" +
                "#         #\n" +
                "###########";
        Bender bender = new Bender(mapa);
        assertEquals("SNNNNWWW", bender.run());
    }

    @Test
    public void test09() {
        String mapa = "" +
                "#######\n" +
                "#T    #\n" +
                "#     #\n" +
                "#     #\n" +
                "#  $ T#\n" +
                "#  X###\n" +
                "#I    #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEEWWWWNNNNNNNNWWWWWW", bender.run());
    }


    @Test
    public void test10() {
        String mapa = "" +
                "#######\n" +
                "#    I#\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#  X  #\n" +
                "#$    #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEENNNNNWWWWSSSSS", bender.run());
    }


    @Test
    public void test11() {
        String mapa = "" +
                "#######\n" +
                "# #  I#\n" +
                "#     #\n" +
                "#  T  #\n" +
                "#  #  #\n" +
                "#T X  #\n" +
                "#$    #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEENNNNNWWSSS", bender.run());
    }

    @Test
    public void test12() {
        String mapa = "" +
                "#######\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#  $  #\n" +
                "#  X  #\n" +
                "#  I  #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SNN", bender.run());
    }


    @Test
    public void test13() {
        String mapa = "" +
                "#######\n" +
                "#    I#\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#$ X  #\n" +
                "#     #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEENNNNNWWWWSSSS", bender.run());
    }

    @Test
    public void test14() {
        String mapa = "" +
                "#######\n" +
                "#T    #\n" +
                "#     #########\n" +
                "#     #        #\n" +
                "#     #$      T#\n" +
                "#     #########\n" +
                "#  X###\n" +
                "#  I  #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEEWWWWNNNNNNNNWWWWWW", bender.run());
    }
}