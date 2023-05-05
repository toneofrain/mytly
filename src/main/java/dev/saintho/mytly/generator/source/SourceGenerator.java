package dev.saintho.mytly.generator.source;

/**
 * 단축 URL로 인코딩하는 소스를 생성하는 인터페이스
 */
public interface SourceGenerator {
	/**
	 * 입력받은 기수에 따른 소스를 리턴한다.
	 * @param base 인코딩 기수
	 * @param original 단축 URL에 연결될 원본 URL
	 * @return long타입 인코딩 소스
	 */
	long generate(int base, String original);
}
