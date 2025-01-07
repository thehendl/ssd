<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>

    <!-- Root Template -->
    <xsl:template match="/catalog">
        <genreSummary>
            <!-- Total Book Count -->
            <totalBooks>
                <xsl:value-of select="count(library/books/book)"/>
            </totalBooks>

            <!-- Average Rating -->
            <averageRating>
                <xsl:variable name="totalRating" select="sum(library/books/book/rating)"/>
                <xsl:variable name="bookCount" select="count(library/books/book)"/>
                <xsl:value-of select="$totalRating div $bookCount"/>
            </averageRating>

            <!-- Process Genres -->
            <xsl:apply-templates select="library/books/book[not(preceding-sibling::book[genre=current()/genre])]"/>
        </genreSummary>
    </xsl:template>

    <!-- Template for Genre Grouping -->
    <xsl:template match="library/books/book">
        <genre name="{genre}">
            <!-- Select all books for this genre -->
            <xsl:for-each select="../book[genre=current()/genre]">
                <book>
                    <title><xsl:value-of select="title"/></title>
                    <author><xsl:value-of select="author"/></author>
                    <price currency="{price/@currency}">
                        <xsl:value-of select="price"/>
                    </price>
                    <rating><xsl:value-of select="rating"/></rating>
                </book>
            </xsl:for-each>
        </genre>
    </xsl:template>
</xsl:stylesheet>
