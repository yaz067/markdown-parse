for i in {1..3}
do
echo "====Test ${i}===="
echo 
# cat test/test/test-file${i}.md
cat test/test/snippet${i}.md
echo
echo "===Result==="
java MarkdownParse.java test/test/snippet${i}.md
done