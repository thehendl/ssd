<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

    <!-- Output settings for HTML -->
    <xsl:output method="html" indent="yes" />

    <!-- Root template -->
    <xsl:template match="/">
        <html>
            <head>
                <title>cinema list</title>
            </head>
            <body>
                <h1>cinema list</h1>
                <!-- Apply templates for all cinema elements -->
                <xsl:apply-templates select="cinemas/cinema" />
            </body>
        </html>
    </xsl:template>

    <!-- Template for each cinema -->
    <xsl:template match="cinema">
        <div>
            <h2>Location: <xsl:value-of select="@location" /></h2>
            <p>Postal Code:
                <xsl:value-of select="/cinemas/postaldata/postal[@id=current()/@postalID]" />
            </p>
            <h3>Cinema Name: <xsl:value-of select="cinemaname" /></h3>
            <!-- Apply templates for movie titles -->
            <xsl:apply-templates select="movies/title" />
        </div>
        <hr />
    </xsl:template>

    <!-- Template for each movie -->
    <xsl:template match="title">
        <div>
            <h4>Movie Title: <xsl:value-of select="normalize-space(text()[1])" /></h4>
            <p>Year: <xsl:value-of select="year/date" /></p>
            <p>Origin: <xsl:value-of select="origin" /></p>
        </div>
    </xsl:template>

</xsl:stylesheet>
