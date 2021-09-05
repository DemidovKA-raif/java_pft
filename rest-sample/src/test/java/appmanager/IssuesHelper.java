package appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import ru.stqa.pft.rest.tests.Issue;

import java.io.IOException;
import java.util.Set;

public class IssuesHelper {

    protected ApplicationManager app;

    public IssuesHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<Issue> getIssueStatus(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get(("https://bugify.stqa.ru/api/issues/" + issueId + ".json")))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
    }

    public Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }
}

