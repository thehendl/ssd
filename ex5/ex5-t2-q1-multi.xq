declare context item := doc("ex5-t2-i1.xml");

<library>
    <inventory>
        {
        for $book in //book
        let $inventory := doc("ex5-t2-i2.xml")//book[@id = $book/@id]
        return
            <book>
                <title>{ $book/title/text() }</title>
                <author>{ $book/author/text() }</author>
                <publisher>{ $book/publisher/text() }</publisher>
                <price>{ $book/price/text() }</price>
                <details>
                    <quantity>{ $inventory/quantity/text() }</quantity>
                    <location>{ $inventory/location/text() }</location>
                    <condition>{ $inventory/condition/text() }</condition>
                    <shelf>{ $inventory/shelf/text() }</shelf>
                    <available>{ $inventory/available/text() }</available>
                </details>
            </book>
        }
    </inventory>
</library>
