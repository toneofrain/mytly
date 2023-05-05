package dev.saintho.mytly.generator.url;

/***
 * 단축 URL을 생성하는 인터페이스
 * see UrlGeneratorConfig
 */
public interface UrlGenerator {
	/***
	 *
	 * @param original 단축 URL에 연결될 원본 URL
	 * @return 생성된 단축 URL String
	 */
	String generate(String original);
}
