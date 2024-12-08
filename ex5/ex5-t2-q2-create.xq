declare context item := doc("ex5-t2-i1.xml");

<catalog>
    <library>
        {
            for $book in //book
            let $bookId := $book/@id
            let $bookTitle := $book/title
            let $bookAuthor := $book/author
            let $bookPrice := $book/price
            let $dynamicelementname := concat("book_", $bookId)  (: dynamic element name with book id :)

            return
                element {$dynamicelementname} {
                    <title>{ $bookTitle }</title>,
                    <author>{ $bookAuthor }</author>,
                    <price currency="USD">{ $bookPrice }</price>,
                    <quantity>{ if ($bookPrice < 10) then 10 else 5 }</quantity>,
                    <condition>{ if ($bookPrice < 10) then "New" else "Used" }</condition>,
                    <location>{ concat("Location_", $bookId) }</location>,
                    <available>{ if ($bookPrice > 10) then "true" else "false" }</available>
                }
        }
    </library>
</catalog>
