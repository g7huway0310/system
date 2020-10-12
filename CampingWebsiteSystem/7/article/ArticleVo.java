package article;

import java.util.ArrayList;
import java.util.List;

public interface ArticleVo {
	
	List<Article> list = new ArrayList<>();
	
	void insertTitle(Article data);
	
	void insertText(Article data);
	
	void updateTitle(Article data);
	
	void updateText(Article data);

	
}
