
# Collection choices

| # | Scenario | Need (order / unique / key→value / sorted) | Interface | Implementation | Why |
| - | -------- | ------------------------------------------ | --------- | -------------- | --- |
| 1 | Ordered catalog; duplicate titles allowed | order | `List<Book>` | `ArrayList<>` | Indexed sequence; duplicates are allowed |
| 2 | Unique registered book IDs | unique | `Set<String>` | `HashSet<>` | No duplicates; fast membership checking |
| 3 | Book ID → current borrower ID | key→value | `Map<String, String>` | `HashMap<>` | Direct key → value lookup |
| 4 | Alphabetically sorted categories | sorted | `Set<String>` | `TreeSet<>` | Unique values with natural sort order |
| 5 | Category → count, sorted by category | key→value + sorted | `Map<String, Integer>` | `TreeMap<>` | Key → value with sorted keys |
| 6 | Checkout history in event order | order | `List<BorrowRecord>` | `ArrayList<>` | Append and iterate in insertion order |

## Ambiguous requirements

1. If unique IDs must also preserve registration order, use `LinkedHashSet`.
2. If borrower lookup must preserve insertion order for display, use `LinkedHashMap`.
3. If many insertions/removals occur in the middle, `LinkedList` is not automatically best. Access pattern and traversal cost matter, so measure rather than assume.