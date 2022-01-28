for i in {1..8}
do
echo "====Test ${i}===="
echo 
cat test-file${i}.md
echo
echo "===Result==="
java MarkdownParse.java test-file${i}.md
done