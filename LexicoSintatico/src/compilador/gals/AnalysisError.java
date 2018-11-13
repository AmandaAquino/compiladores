package compilador.gals;

public class AnalysisError extends Exception
{
    private int position;
    private String token;
    
    public AnalysisError(String msg, int position, String token) {
		super(msg);
		this.position = position;
		this.setToken(token);
	}

    public AnalysisError(String msg, int position)
    {
        super(msg);
        this.position = position;
    }

    public AnalysisError(String msg)
    {
        super(msg);
        this.position = -1;
    }

    public int getPosition()
    {
        return position;
    }

    public String toString()
    {
        return super.toString() + ", @ "+position;
    }
    
    public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
