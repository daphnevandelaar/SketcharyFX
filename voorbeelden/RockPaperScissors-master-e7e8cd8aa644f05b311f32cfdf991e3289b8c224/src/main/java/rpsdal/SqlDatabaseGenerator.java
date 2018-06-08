package rpsdal;

public class SqlDatabaseGenerator {

    String playerTable = "USE [RockPaperScissors]\n" +
            "\n" +
            "/****** Object:  Table [dbo].[Players]    Script Date: 5/8/2018 7:52:46 PM ******/\n" +
            "SET ANSI_NULLS ON\n" +
            "\n" +
            "SET QUOTED_IDENTIFIER ON\n" +
            "\n" +
            "CREATE TABLE [dbo].[Players](\n" +
            "\t[Id] [bigint] IDENTITY(1,1) NOT NULL,\n" +
            "\t[UserName] [nvarchar](50) NOT NULL,\n" +
            "\t[Password] [nvarchar](50) NULL,\n" +
            " CONSTRAINT [PK_Players] PRIMARY KEY CLUSTERED \n" +
            "(\n" +
            "\t[Id] ASC\n" +
            ")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\n" +
            ") ON [PRIMARY]\n" ;

    String roundResultTable = "USE [RockPaperScissors]\n" +
            "\n" +
            "/****** Object:  Table [dbo].[RoundResults]    Script Date: 5/8/2018 7:53:20 PM ******/\n" +
            "SET ANSI_NULLS ON\n" +
            "\n" +
            "SET QUOTED_IDENTIFIER ON\n" +
            "\n" +
            "CREATE TABLE [dbo].[RoundResults](\n" +
            "\t[Id] [bigint] IDENTITY(1,1) NOT NULL,\n" +
            "\t[Player1Name] [nvarchar](200) NULL,\n" +
            "\t[Player2Name] [nvarchar](200) NULL,\n" +
            "\t[Player1Choice] [nvarchar](200) NULL,\n" +
            "\t[Player2Choice] [nvarchar](200) NULL,\n" +
            "\t[WinningPlayerName] [nvarchar](200) NULL,\n" +
            " CONSTRAINT [PK_Round] PRIMARY KEY CLUSTERED \n" +
            "(\n" +
            "\t[Id] ASC\n" +
            ")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\n" +
            ") ON [PRIMARY]\n";

    public void createDatabase()
    {
        SqlDataContext context = new SqlDataContext();
        context.executeNonQuery(playerTable);
        context.executeNonQuery(roundResultTable);

    }
}
