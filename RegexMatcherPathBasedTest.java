// following line means that this files is part of a package starter, which contains classes with executable main and test methods. 
//package starter;

// following line imports the classes of a package matcher (in this case this package contains the classes RegexMatcher and RegexMatchResult) 
//import matcher.*;

import org.junit.Test;
import static org.junit.Assert.*;


public class RegexMatcherPathBasedTest{

	private void check(String regex, String text, int correctStartingPosition, String correctMatchedString){
		RegexMatchResult calculatedResult = RegexMatcher.matchPathBased(regex, text);
		boolean c1 = (calculatedResult.getStartingPosition() == correctStartingPosition);
		boolean c2 = calculatedResult.getMatchedString().equals(correctMatchedString);
		assertTrue(c1 && c2);
	}
	
	@Test public void noMatch() {check("a", "b", -1, "");} // This means that your program should return a MatchingResultForDFAEngine with startingPosition -1, if there is no match. 
	@Test public void t01() {check("a", "a", 0, "a");}
	@Test public void t02() {check("1", "1", 0, "1");}
	@Test public void t03() {check("b", "baa", 0, "b");}
	@Test public void t04() {check("b", "aba", 1, "b");}
	@Test public void t05() {check("b", "aab", 2, "b");}
	@Test public void t06() {check("ab", "baba", 1, "ab");}
	@Test public void t07() {check("(ab)a", "baba", 1, "aba");}
	@Test public void t08() {check("aba", "babab", 1, "aba");}
	@Test public void t09() {check("c", "a", -1, "");}
	
	@Test public void t10() {check("(ab)*", "cababc", 1, "abab");}
	@Test public void t11() {check("(ab)*", "cabababc", 1, "ababab");}
	@Test public void t12() {check("(ab)*", "cabababc", 1, "ababab");}
	@Test public void t13() {check("(ab)*c", "cabc", 0, "c");}
	@Test public void t14() {check("(ab)*c", "ababc", 0, "ababc");}
	
	@Test public void t15() {check("a|b", "a", 0, "a");}
	@Test public void t16() {check("a|b", "b", 0, "b");}
	@Test public void t17() {check("a|b|c", "c", 0, "c");}
	@Test public void t18() {check("(a|b)|c", "c", 0, "c");}
	@Test public void t19() {check("(ab|cd)", "kab", 1, "ab");}
	@Test public void t20() {check("(ab|cd)", "kcd", 1, "cd");}
	@Test public void t21() {check("(ab|cd)", "a", -1, "");}

	@Test public void t22() {check("(ab|cd)*", "zzababcdcdzz", 2, "ababcdcd");}
	@Test public void t23() {check("(ab|cd)*", "zzabcdabcdzz", 2, "abcdabcd");}
	@Test public void t24() {check("k((ab|cd)*)", "zkabcdabcdmz", 1, "kabcdabcd");}  //abgeändert (klammern)
	@Test public void t25() {check("k((ab|cd)*)m", "zkabcdabcdmz", 1, "kabcdabcdm");}//abgeändert (klammern)
	
	@Test public void t26() {check("(ab|c*d)*", "zzzababcdccdabccccd", 3, "ababcdccdabccccd");}
	//	Write also your own unit tests here
	@Test public void s01() {check("A((AG|AA)*)",   "AAABAAG",           0,"AAA"     );}
	@Test public void s02() {check("AE((AG|AA)*)",  "----AEAGAAA---",    4,"AEAGAA"  );}
	@Test public void s03() {check("AE((AG|AA)*)",  "----AEAGAAAE---",   4,"AEAGAA"  );}
	@Test public void s04() {check("AE((AG|AA)*)",  "----AEAGAABAE---",  4,"AEAGAA"  );}
	@Test public void s05() {check("AE((AG|AA)*)",  "----AEAGAAGAE---",  4,"AEAGAA"  );}
	@Test public void s06() {check("AE((AG|AA)*)",  "----AEAGAAAAE---",  4,"AEAGAAAA");}
	@Test public void s07() {check("AE((AG|AA)*)",  "----AEAGAAABAE---", 4,"AEAGAA"  );}
	@Test public void s08() {check("AE((AG|AA)*)",  "----AEAGAAAAAE---", 4,"AEAGAAAA");}
	@Test public void s09() {check("AE((AG|AB)*)",  "----AEAGABAAAAE---",4,"AEAGAB"  );}
	@Test public void s10() {check("AE((AG|AB)*)",  "----AEAGABCAAAE---",4,"AEAGAB"  );}
	@Test public void s11() {check("AE((AG|AB)*)",  "----AEAGABACAAE---",4,"AEAGAB"  );}
	@Test public void s12() {check("AE((AG|AB)*)",  "----AEAGABABAAE---",4,"AEAGABAB");}
	@Test public void s13() {check("AE((AG|AB)*)",  "----AEAGABAEAAE---",4,"AEAGAB"  );}
	@Test public void s14() {check("AE((AG|AB)*)",  "----AEAGABEAAAE---",4,"AEAGAB"  );}
	@Test public void s15() {check("AE((AG|AB)*)E", "----AEAGABEAAAE---",4,"AEAGABE" );}
	@Test public void s16() {check("AE((AG|AB)*)EA","----AEAGABEAAAE---",4,"AEAGABEA");}

	@Test public void b01() {check("(ab)*","aab",1,"ab");}
	@Test public void b02() {check("(ab)?","aaabcde",2,"ab");}
	@Test public void b03() {check("(ab)+","cqaababrq",3,"abab");}
	@Test public void b04() {check("((ab)?)*","deabab",2,"abab");}
	@Test public void b05() {check("((ab)+)b)","deababb",2,"ababb");}
}
