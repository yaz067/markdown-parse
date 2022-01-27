for i in {1..9}
do
echo "====Test ${i}===="
cat test-file${i}.md
java MarkdownParse.java test-file${i}.md
done